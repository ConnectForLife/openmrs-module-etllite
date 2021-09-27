export const DBConnectionType = [
    {
        value: 'MYSQL',
        defaultUrl: 'jdbc:mysql://db-host:3306/database-name'
    },
    {
        value: 'POSTGRESQL',
        defaultUrl: 'jdbc:postgresql://db-host:db-port/database-name'
    },
    {
        value: 'MSSQL',
        defaultUrl: 'jdbc:sqlserver://db-host:db-port;databaseName=database-name'
    }
]