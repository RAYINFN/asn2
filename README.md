# Staff Rating Web Application

## AI Tools Used

This project is enhanced by **Claude by Anthropic** Claude Sonnet 4.6

## How Claude Helped on This Project

### Debugging & Error Fixing
    Diagnosed the `HHH100046` JDBC connection error and configured `application.properties` with H2 in-memory database settings
    Fixed the `UnsatisfiedDependencyException` caused by a typo in the JPA repository method name (`existsbyEmail` → `existsByEmail`)
    Identified and corrected invalid Maven artifact IDs in `pom.xml`
    Repaired issues on application.properties, resolved deployment issue

### Code Generation
    Added missing getters and setters to the `StaffRating` entity so JPA and form binding could work
    Fixed a missing `/` in the post-create redirect URL in `MainController`

### Testing
    Provide suggestions based on current java package and easy to write
