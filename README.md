# Predicted Grades

This project is the Student focused Predicted Grades data collection system. It is used to allow the prospective students to enter the data regarding their GCSE and BTEC qualification that they received at school directly into the CID database. There are two primary components that this system uses:

* API - The API layer that interacts with the SQL database. 
* UI - The user interface for the system

## API

This part of the project is a Spring Boot application that can be run using the command: `gradlew bootRun`. This has to be executed for inside the api folder.

## UI - User Interface

This part of the project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.1.8.

### Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Building docker images

The following commands can be used to build the docker images for the Predicted Grades API and UI:

```bash
./scripts/build-docker.sh --component { COMPONENT NAME } -v { VERSION }
```

This command executes a shell script that will run the various docker commands to build the image based on the current state of the source code in the directory. If you want more information about what is happening in the background you can add the argument _--verbose_ to log all output to the console. For information about the other arguments use the _--help_ argument.

The script will output a log file into the base folder with the file name __build-{ COMPONENT NAME }.log__ with details of build process. This is the same output that appears with the _--verbose_ argument.

By default the build script will calculate the appropriate Docker Image Tag for the particular build. The tag will be something like registry.reigate.ac.uk/predicted/api:0.0.5 (see below for an explanation of Docker Image Tags). The tag for your newly created image will appear on the command line, this will be used later for running, publishing and deploying the docker image.
