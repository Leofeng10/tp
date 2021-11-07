---
layout: page
title: Ammar's Project Portfolio Page
---

### Project: TutAssistor

TutAssistor is a desktop application for private tutors to manage tuition class timeslots. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface). The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It has about 20 kLoC.

Given below are my contributions to the project.

### Summary of Contributions

Access my [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=amzhy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=amzhy&tabRepo=AY2122S1-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) to view the code that I have contributed.

#### Enhancements implemented
* **Create edit features**: Designed and created `EditCommand`, `EditClassCommand`, `EditClassDescriptor` and its corresponding `Parser` classes. (Pull request [\#125](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/125))
  * What it does: Allows user to edit any details of a student or tuition class with the exception of remarks in **TutAssistor**
  * Justification:  This makes the user experience much more pleasant as now they do not have to delete and recreate
students or classes in order to change a few details.
  * Highlights:
  
* **Create delete features**: Designed and created `DeleteCommand`, `DeleteClassCommand` and its corresponding `Parser` classes. (Pull requests [\#46](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/46))
  * What it does: Allows user to delete a student or tuition classe in **TutAssistor**
  * Justification:  This enables the user to remove entire records pertaining to a student or a tuition class
entire students or classes in order to change a few details.
  * Highlights:
  
* **Implement `RemoveStudent` feature**: Designed and created `RemoveStudentCommand`, `RemoveStudentCommandParser`. (Pull request [\#58](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/58))
  * What it does: Allows user to remove an existing student from a tuition class in **TutAssistor**
  * Justification:  This is more convenient for the user as now they do not have to delete and recreate
  * a tuition class when a student drops out of a class.
  * Highlights:
  
* **Refactor Timeslot class**: Refactor Timeslot class to use Java `LocalTime` and `Date` for class timings instead of`String`. (Pull request [\#125](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/125))
  * What it does: Parses and saves the timing of any tuition class as `Date` and `LocalTime` to compare timeslots across classes. 
  * Justification: Previously, the time slot was a `String` which created parsing errors causing classes to be created at invalid timings. Multiple classes
were also scheduled simultaneously due to these errors. Following the refactoring of the `Timeslot`, classes were created only at valid times and the timetable conflicts were detected more easily 
using the existing Java API.
  * Highlights:
    * This is a major refactor as the `Timeslot` is crucial for the logic of `Timetable` and management of tuition classes. 

* **Refactor some commands to use multiple indexes**: Refactor `DeleteCommand`, `DeleteClassCommand` and `RemoveStudentCommand` to use multiple student or class indexes as arguments. (Pull request [\#65](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/65))
  * What it does: Allows user to delete multiple students or tuition classes as well as remove multiple students from a class.
  * Justification: Improve efficiency so that the user is able to remove multiple students or classes using one command instead of 
repeating similar commands multiple times.

* **Testing**: Create tests for the edit, delete commands and their related classes to increase coverage. (Pull request)

  <div style="page-break-after: always;"></div>
#### Contributions to the User Guide
Added to the FAQ and Introduction, formatted figures and enabled quicklinks for the command summary.(Pull Request [\#139](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/139))

#### Contributions to the Developer Guide
Added the implementation details of edit commands (Pull Request [\#135](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/135))

<div style="page-break-after: always;"></div>

#### Contributions to the team-based tasks
* Proposed the feature to enable command shortcuts for long commands
* Proposed the idea of input history navigation feature to improve the design of TutAssistor to model a CLI application
* Enabled status checks and approvals for all PRs to prevent CI failures for the team's repository

#### Review/mentoring contributions
* PRs reviewed: [\#198](https://github.com/AY2122S1-CS2103T-T12-4/tp/pull/198)

#### Contributions beyond the project team
* Reported [20 bugs](https://github.com/amzhy/ped/issues) in group F13-2's tP during mock PE.

