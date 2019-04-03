
[![Build Status](https://img.shields.io/travis/ElHassanBaghrar/MyBench/master.svg?style=flat-square)](https://travis-ci.org/ElHassanBaghrar/MyBench)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/185f393e20ed45d891307dbca782f2a9)](https://app.codacy.com/app/ElHassanBaghrar/MyBench?utm_source=github.com&utm_medium=referral)
[![License](https://img.shields.io/github/license/ElHassanBaghrar/MyBench.svg?style=flat-square)](LICENSE)
[![Version](https://img.shields.io/github/tag-date/ElHassanBaghrar/MyBench.svg)](build.gradle)
image:https://img.shields.io/github/release/ElHassanBaghrar/MyBench.svg?style=plastic[GitHub release]
[![Waffle.io - Columns and their card count](https://badge.waffle.io/ElHassanBaghrar/MyBench.svg?columns=all)](https://waffle.io/ElHassanBaghrar/MyBench)

# MyBench

MyBench, une application android de géolocalisation pour trouver un banc dans la region Ile de France.

## Qu'est ce que c'est ?

C'est une application android qui permettra aux personnes qui reside en Île de France de trouver facilement un banc proche de leur géolocalisation pour leur pose dejeuner ou aux touristes pour se reposer lors de leurs differentes visites à Paris.

## Fonctionnalités 


## Système de Build
* [Gradle](https://gradle.org/)

## Téléchargement

L'application peut-être téléchargée ici :  https://github.com/ElHassanBaghrar/MyBench/releases/download/v1.0-alpha/MyBench_v1.0-alpha.apk

L'application exige la permission d'accéder à la géolocalisation pour proposer à l'utilisateur les bancs les plus proches.

## Installation

### Prérequis

Avant de pouvoir utiliser notre projet, il faut procéder aux installations suivantes.

#### Installer Android Studio et le SDK

- Télécharger Android studio et l'installer : [Télécharger Android Studio](https://developer.android.com/studio/index.html)

- Télécharger le SDK : 

Depuis la fenêtres de bienvenue : 
  Configure > SDK Manager > SDK TOOLS > Cocher Android SDK Tools > OK
  
#### Installer Git

- Télécharger et installer Git : [Télécharger Git] (https://gitforwindows.org/)

### Cloner MyBench

Une fois Git installé, il suffit de suivre les instructions ci-dessous.

#### Lier Git à Android Studio 

Depuis Android studio : 
  File > Settings > Version Control > Git > Dans " path to git Excecutable ", coller le chemin du git.exe > OK
  
#### Cloner le répertoire MyBench

Depuis Android studio : 
  VCS > Checkout from version control > Git > Dans " Git Repository URL ", coller https://github.com/ElHassanBaghrar/MyBench.git > OK


#### Builder l'apk
cd ./MyBench
./gradlew
ou sur windows :
./gradlew.bat

et enfin :

adb install -r MyBench.apk


 Et voila ! le projet est prêt pour utilisation.

## Auteurs
* **Kadjo ASSOHOUN** - [Github](https://github.com/Kadjoassohoun)
* **ElHassan BAGHRAR** - [Github](https://github.com/ElHassanBaghrar)
* **Sami BOULHAZAIZ** - [Github](https://github.com/Henley74)

  

