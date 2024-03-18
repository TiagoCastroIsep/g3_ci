# Smart Home
Hey there! 👋

Welcome to the Smart Home project developed by Group 3 of the Switch 2023/2024. 
This project utilizes **Java** and **Maven**, and includes unit testing through **JUnit 5**.

---
## Installation
To install and run this project locally, you need to follow these steps:

1. Ensure you have Java and Maven installed on your machine.
2. Clone this repository using the command: `git clone https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-3.git`
3. Navigate to the project directory: `cd your-project-directory`
4. Build the project using Maven: `mvn clean install`
5. Run the project: `java -jar target/your-jar-file.jar`

## Usage
This project is a simulation of a smart home system. You can add rooms, devices, sensors and actuators to your home, and control them through the provided interfaces.

## Contribution Guidelines
We welcome contributions from the community. To contribute:

1. Fork this repository.
2. Create a new branch for your changes.
3. Make your changes in your branch.
4. Submit a pull request with your changes.

Please make sure your code follows our style guidelines and passes all tests before submitting your pull request.

## Testing
To run the tests for this project, use the command: `mvn test`
The tests we created are isolation tests for the domain using mocks and stubs. Classes like GPS, dimensions, and others are tested using integration tests.
The controllers used integration tests to test the interaction between the domain classes.

[//]: # (## License)

[//]: # (This project is open source.)

## Contact Information
For support or bug reporting, please submit an issue on this repository.

---
## Documentation
Here are some helpful resources about the project:

- [Introduction](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-3/wiki/Home)
- [Glossary](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-3/wiki/Glossary)
- [Diagrams](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-3/wiki/Project-Diagrams)
- [javadoc](https://smart-home-g3.netlify.app/)

Feel free to explore our project's wiki for detailed information and resources.

---
## Authors
- [Aline Emily](https://github.com/line-em)
- [Ana Silva](https://github.com/AnaSilvaSwitch)
- [João Gonçalves](https://github.com/KennyPorto)
- [Luís Gomes](https://github.com/luisgomes52)
- [Mariana Pereira](https://github.com/marianafpereira)
- [Pedro Silva](https://github.com/pedroswitch)
- [Solange Oliveira](https://github.com/Solange-o)
- [Tiago Castro](https://github.com/TiagoCastroIsep)

[//]: # ()
[//]: # (## Style Conventions)

[//]: # (- `Plural variables for lists: devices, rooms, sensors.`)

[//]: # (- `_ / underscore for private variables`)

[//]: # (- `configure for changes asked in a user story, set for minor changes.`)

[//]: # (- `Do not use 'of' in variables.`)

[//]: # (- `Use add, preferably. `)

[//]: # (- `Camelcase is preferable`)

[//]: # ()
[//]: # (## Commit Conventions)

[//]: # (   To maintain a clear and consistent history of changes, each commit message should be structured as follows:)

[//]: # (   )
[//]: # (   [keyword] [US nº] #nºissue - Verb in past tense &#40;e.g., Fixed, Added, Optimized&#41; followed by the rest of the message.)

[//]: # ()
[//]: # (   Where:)

[//]: # (- `[keyword]` is one of the predefined types listed below.)

[//]: # (- `[US nº]` refers to the User Story number &#40;if applicable&#41;.)

[//]: # (- `#nºissue` refers to the issue number on our issue tracker that this commit is related to &#40;if applicable&#41;.)

[//]: # ()
[//]: # (   ### Keywords)

[//]: # ()
[//]: # (   - `feat` – Introduces a new feature to the codebase.)

[//]: # (   - `fix` – Fixes a bug in the existing code.)

[//]: # (   - `chore` – Includes changes that do not relate to a fix or feature and don't modify `src` or `test` files &#40;e.g., updating dependencies&#41;.)

[//]: # (   - `refactor` – Involves code refactoring that neither fixes a bug nor adds a feature.)

[//]: # (   - `docs` – Makes updates to documentation, such as the README or other markdown files.)

[//]: # (   - `style` – Applies changes that do not affect the meaning of the code, primarily related to code formatting &#40;e.g., whitespace, missing semi-colons&#41;.)

[//]: # (   - `test` – Pertains to adding new tests or correcting existing ones.)

[//]: # (   - `perf` – Implements performance improvements.)

[//]: # (   - `ci` – Relates to continuous integration changes.)

[//]: # (   - `build` – Affects the build system or external dependencies.)

[//]: # (   - `revert` – Reverts a previous commit.)

[//]: # ()
[//]: # (   These conventions facilitates the review process and enhances the clarity of the project's history for all contributors.)

[//]: # (   )
[//]: # ()
[//]: # (## Tests:)

[//]: # (- `void, without public`)

[//]: # (- `@BeforeEach: setup&#40;&#41;`)

[//]: # ()
[//]: # (## Parameters)

[//]: # (- `without underscore`)

[//]: # ()
