## Full Tests (@SpringBootTest)

| `ValidationTest.java` | Validation on insert wrong data |
| `MainControllerTest.java` | Test on Create profile |
| `AccessTest.java` | Test on Save and Delete |

## Why @SpringBootTest?

@SpringBootTest loads the full Spring Boot application context. This allows the tests to check whether the controller, service, database and validation all work together correctly and effectively. Although it is slower than slice testing, it better matches the real application behavior.

## Running the tests

```Powershell
PS> ./mvnw test
```
