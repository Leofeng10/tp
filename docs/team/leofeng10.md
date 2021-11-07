---
layout: page
title: Feng Zhunyi's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It has about 20 kLoC.

Given below are my contributions to the project.
Access my [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=leofeng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=amzhy&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) to view the code that I have contributed.
  
* Enhancements implemented
  * **Create Tuition Model**: Designed and created `ClassLimit`, `ClassName`, `Timeslot`, `TuitionClass`, `UniqueTuitionList`. (Pull request [\#10](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/10/commits/8cdb3def342ca3d832e6668b2928ec162eff4792))
    * What it does: Allows user to edit any details of a student or tuition class with the exception of remarks in **TutAssistor**
    * Justification: This is the most basic model for our app as our app aiming to manage tutors classes. Thus we need to have a
      model for those classes and encapsulates classes' attributes in the model.
  
  * **Create add tuition features**: Designed and created `AddClassCommand` and its corresponding `Parser` classes. (Pull requests [\#14](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/14/commits/6a86752ef9acf8854b2a18330402d6887498b7a5))
    * What it does: Allows user to create a tuition class in **TutAssistor**
    * Justification:  This allows user to create a tuition class in order to record the class his has.
  
  * **Create view today tuition classes features**: Designed and created `ViewTodayTuitionCommand` and its corresponding `Parser` classes, and modified
    `MainWindow`, `LogicManager` and relative files. (Pull requests [\#103](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/103))
    * What it does: Allows user to check today tuition classes in **TutAssistor** when he opens the app and when he wants
    * Justification:  This allows user to better arrange their time after knowing what classes they have today.
  
  * **Create AddRemark features**: Designed and created `AddRemarkCommand` and its corresponding `Parser` classes. (Pull requests [\#3](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/3))
    * What it does: Allows user to add remark to students or tuition classes in **TutAssistor**
    * Justification: Notes are often need for a tutor to keep some records for students and classes.
    
  * **Create `Tuition` UI**: Designed and created `TuitionCard`, `TuitionListPanel`, and modified `MainApp`, `MainWindow` (Pull requests [\#10](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/10/commits/74ba546fabb33fd65299a005b4e321116ab52716))
    * What it does: Allows user to check tuition classes information in **TutAssistor**
    * Justification: To check tuition information, a UI is needed to display hte information.
  
  * **Manage Timeslot conflict**: Designed and created the format of timeslot, and the algorithm for conflict detection.(Pull requests [\#41](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/41))
    * What it does: Allows user to add tuition classes in **TutAssistor** without time conflict
    * Justification:  **TutAssistor** allows users to manage their tuition classes, the our app needs to ensure that users
      cannot add different tuition classes in the same time slot.
    
  * **Create store `Tuition` features**: Designed and created `JsonAdaptedTuition`, and modified 
    `AddressBookStorage`, `JsonAddressBookStorage`, `JsonSerializableAddressBook` to create, read tuition class . (Pull requests [\#10](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/10))
    * What it does: All user to store a tuition class into the json file in **TutAssistor**
    * Justification:  As users need to check their tuiton classes they have created, the class information need to be stored. We choose to store
      the data into json file because it is easy to export and import.
    
  * **Testing**: Create tests for the `ClassLimit`, `Timeslot`, `ClassName`, `UniqueTuitionList` to increase coverage. (Pull request [\#83](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/83))
  

* Contributions to the User Guide
  * Added documentation for the features `addclass` and `today` (Pull Request [\#109](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/109))

* Contributions to the Developer Guide
  * Added the implementation details of time conflict management (Pull Request [\#108](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/108))
  * Added the UML diagram for time conflict management (Pull Request [\#203](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/203))
  * Added Use cases for **TutAssistor**.

* Contributions to the team-based tasks
  * Proposed the attributes of a tuition class
  * Proposed the UI to display Tuition classes and students
  * Proposed the format of time slot, and the way to detect time conflict
  * Proposed using hashcode as the unique ID for a tuition class for easier referencing

* Review/mentoring contributions
  * PRs reviewed: [\#42](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/42)

* Contributions beyond the project team
  * Reported [4 issues](https://github.com/Leofeng10/ped/issues) in group W13-1's tP during mock PE.

