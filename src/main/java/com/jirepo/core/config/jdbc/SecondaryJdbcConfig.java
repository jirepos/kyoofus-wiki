package com.jirepo.core.config.jdbc;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jirepo.core.util.SpringContextUtils;

/** MyBatis 사용 기본 DB 설정 */
@Configuration
@EnableTransactionManagement
@MapperScan(
    basePackages="com.jirepo",   // 설정된 패키지 경로에서부터 Mapper Interface를 스캔한다. 여러 경로이면 콤마(,)로 구분
    annotationClass=SecondaryMapper.class,
    sqlSessionFactoryRef = "secondarySqlSessionFactory",
    sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class SecondaryJdbcConfig {


  static class MapperLocation {
    private String[] mapperLocations;

    public String[] getMapperLocations() {
      return mapperLocations;
    }

    public void setMapperLocations(String[] mapperLocations) {
      this.mapperLocations = mapperLocations;
    }
  }



  @Bean(name="secondaryMapperLocation")
  @ConfigurationProperties(prefix="datasource-configs.secondary.datasource")
  public MapperLocation mapperLocation() {
    return new MapperLocation();
  }

  

  @Primary
  @Bean(name="secondaryDataSource")
  @ConfigurationProperties(prefix = "datasource-configs.secondary.datasource") // yaml 파일에서 값을 읽는다
  public DataSource dataSource() {
    
    
    // final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		// dsLookup.setResourceRef(true);
		// String jndiName =  env.getProperty("jndiName");   //"jdbc/NaonekpDS";
		// jndiName = jndiName.replaceAll("java:comp/env/","");
		// DataSource dataSource = dsLookup.getDataSource(jndiName.trim());

    return DataSourceBuilder.create().build();
    
    
    // String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
    // if(activeProfiles == null)  return dataSource; 
    // boolean result = false;
    // for(String profile : activeProfiles) {
    //   if(profile.equals("sqllog")) {
    //     result = true; 
    //     break;
    //   }
    // }
    // if(result) {
    //   Log4JdbcCustomFormatter formatter = new Log4JdbcCustomFormatter();
		// 	// formatter.setLoggingType(LoggingType.MULTI_LINE);
		// 	formatter.setLoggingType(LoggingType.SINGLE_LINE);
		// 	//formatter.setSqlPrefix("EXECUTED QUERY: \r");
		// 	formatter.setSqlPrefix("EXECUTED QUERY: ");
		// 	// diObjectFactoryBean bean = jndiObjectFactoryBean();
		// 	// Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource((DataSource)
		// 	// jndiObjectFactoryBean());
		// 	Log4jdbcProxyDataSource _dataSource = new Log4jdbcProxyDataSource(dataSource);
		// 	_dataSource.setLogFormatter(formatter);
		// 	return _dataSource;
    // }
    // return dataSource; 
  }//:
  
  

  @Primary
  @Bean(name = "secondarySqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
         SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
         sqlSessionFactoryBean.setDataSource(dataSource);
         //sqlSessionFactoryBean.setTypeAliasesPackage("com.test.api.entity, com.test.api.vo");
         MapperLocation mapperLocation = (MapperLocation)applicationContext.getBean("secondaryMapperLocation");
         Resource[] resources = SpringContextUtils.getResources(applicationContext, mapperLocation.getMapperLocations());
         sqlSessionFactoryBean.setMapperLocations(resources);
         //sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:com/sogood/mapper/**.xml"));
         org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
         mybatisConfig.setMapUnderscoreToCamelCase(true);
         sqlSessionFactoryBean.setConfiguration(mybatisConfig);
        //  // type handler 
        //  sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[] {
        //    new BlogPostCode.TypeHandler()  // 의존성 문제가 생기네 
        //  });
         //mybatisConfig.setJdbcTypeForNull(JdbcType.NULL); // 좀 더 확인해 보고
         return sqlSessionFactoryBean.getObject();
  }//:

  
  @Primary
  @Bean(name = "secondarySqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

	@Primary
	@Bean(name = "secondaryTransactionManager")
	public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("secondaryDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}//:
}///~
