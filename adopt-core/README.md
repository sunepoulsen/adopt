# Adopt Core module

## System properties

| Name                  | Mandatory | Description                                                                                       | Default Value         |
| --------------------- | --------- | ------------------------------------------------------------------------------------------------- | --------------------- |
| adopt.profile.name    | No        | Read properties from `application-<adopt.profile.name>.properties` after `application.properties` | -                     |
| adopt.os.name         | No        | Property to override the use of `os.name`                                                         | -                     |
| os.name               | Yes       | Operating system name                                                                             | -                     |

## Application properties

| Name                  | Mandatory | Description                                                                                       | Default Value         |
| --------------------- | --------- | ------------------------------------------------------------------------------------------------- | --------------------- |
| adopt.local.directory | No        | Used to specify the application directory for local use.                                          | -                     |
