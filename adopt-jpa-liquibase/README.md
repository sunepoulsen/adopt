# Adopt JPA Liquibase module

This module implements liquibase database migrations for the Adopt platform.

## Application properties

| Name                | Mandatory | Description                                                                                       | Default Value         |
| ------------------- | --------- | ------------------------------------------------------------------------------------------------- | --------------------- |
| liquibase.migrate   | No        | Enable liquibase migration - true/false                                                           | true                  |
| datasource.url      | No        | JDBC url to the database to migrate                                                               | false                 |
| datasource.user     | No        | Username for the database connection                                                              | false                 |
| datasource.password | No        | Password for the database connection                                                              | false                 |

## Changelog

The liquibase changelog needs to be placed in the package `db/changelog` and the package needs to be open in the module that contains it:

```
opens db.changelog;
```
