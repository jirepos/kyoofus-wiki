


class GraphQLQueryBase {
    

    /** graphql URL */
    protected readonly endPoint: string = 'http://localhost/graphql';

    protected  getOptions(query: string, variables: any): any {
        let options = { 
            method: 'POST',
            headers:
            {
                 'Content-Type': 'application/json',
            },
            //body: JSON.stringify({ query })
            body: { query , variables }
        }// options 
        return options; 
    }//:
}///~

export { GraphQLQueryBase }