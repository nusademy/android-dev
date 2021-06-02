# android-dev

## Description
This repository is use for android developer to collaborate with others developers

## Features
- Login and register for users and teachers
- edit profile users,and teachers.
- Request Teaching 
- Reject/Approve Teaching
- Video Naration

# Installation
The following are the installation steps both locally and in production.
## System Requireements
Based on the official documentation from Android Studio and our implementation, here are the minimum system requirements to run the this project
- 64-bit Microsoft® Windows® 8/10
- x86_64 CPU architecture; 2nd generation Intel Core or newer, or AMD CPU with support for a Windows Hypervisor
- 8 GB RAM or more
- 8 GB of available disk space minimum (IDE + Android SDK + Android Emulator)
- 1280 x 800 minimum screen resolution

## Local
The following are the steps to run this application locally:
1. Make sure that git, nodejs, npm, and posgreSQL are installed on your system.
2. Clone this repository with the following command.
```bash 
git clone https://github.com/nusademy/android-dev.git
```
3. run sync project with Gradle Files
4. Wait a few moments for the application to run successfully


## CI/CD


1. Before setting up CI/CD, first connect Cloud Build with Repository. Cloud Build -> Trigger -> Connect Repository.
2. Create a Trigger for CI/CD, specify the repository, then in the substitution variables enter the following key (We have provided cloudbuild.yaml in the repository.):   
```bash
_BUCKET  =  example
```
3. Click Save, then Run Trigger.

# END

[![Continuous Integration Workflow](https://github.com/nusademy/android-dev/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/nusademy/android-dev/actions/workflows/main.yml)
