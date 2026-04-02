# rimfrost-framework-handlaggning-adapter changelog

Changelog of rimfrost-framework-handlaggning-adapter.

## 0.1.17 (2026-04-02)

### Bug Fixes

-  Allow null for processInstansId ([edb7d](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/edb7d5a48927435) Lars Persson)  

## 0.1.16 (2026-04-02)

### Bug Fixes

-  tar bort processInstansId från toPostHandlaggningRequest ([13c14](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/13c14f13f554a06) Ulf Slunga)  
-  lägger till producerade resultat till beslutsrad. tar bort processInstansId från ny handläggning. ([34f11](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/34f11a916f101cb) Ulf Slunga)  

## 0.1.15 (2026-04-01)

### Bug Fixes

-  tar bort underlag från Uppgift ([042cd](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/042cd79f1747fdc) Ulf Slunga)  

## 0.1.14 (2026-03-31)

### Bug Fixes

-  Add support for setting beslut on Yrkande ([03f90](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/03f90336d67f9ba) Lars Persson)  

## 0.1.13 (2026-03-25)

### Bug Fixes

-  renaming Handlaggning HandlaggningUpdate ([09277](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/092770bc3958eb4) Ulf Slunga)  

## 0.1.12 (2026-03-19)

### Bug Fixes

-  Mark avslutadTS as nullable ([e9300](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/e930040288d2431) Lars Persson)  

## 0.1.11 (2026-03-19)

### Bug Fixes

-  Set yrkandeStatus and avslagsanledning in model ProduceratResultat ([1357b](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/1357b1e0aef54d9) Lars Persson)  

## 0.1.10 (2026-03-19)

### Bug Fixes

-  yrkandestatus som enum ([91b63](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/91b6369d82310d2) Ulf Slunga)  
-  yrkandestatus och avslagsanledning i produceratresultat ([22cde](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/22cde2dad3da9da) Ulf Slunga)  
-  sätter uppgiftstatus och FSSAinformation i toApiUppgift ([7af60](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/7af60cda75ce7e1) Ulf Slunga)  

## 0.1.9 (2026-03-18)

### Bug Fixes

-  uppdatera mot modell ([bdfd3](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/bdfd3306d2b1d55) Ulf Slunga)  

## 0.1.8 (2026-03-11)

### Bug Fixes

-  nullpointer in mapping enums that are nullable ([ac9cb](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/ac9cbed1c78e847) Nils Elveros)  

## 0.1.7 (2026-03-10)

### Bug Fixes

-  uppdatering av yrkanderoll ([4de59](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/4de59cb5e6cbc4b) Ulf Slunga)  

## 0.1.6 (2026-03-06)

### Bug Fixes

-  add ersattningstatus to patch ([99e71](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/99e714d3427ad46) Nils Elveros)  

## 0.1.5 (2026-03-03)

### Bug Fixes

-  Rename kundbehovsflode to handlaggning ([5936b](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/5936b3a3f4d4f22) Lars Persson)  

## 0.1.4 (2026-03-03)

### Bug Fixes

-  Update README.md ([e447a](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/e447a5aa7f3de0f) Lars Persson)  

## 0.1.3 (2026-03-03)

### Bug Fixes

-  Rename kundbehovsflode to handlaggning ([e90aa](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/e90aa71bc7eb7d2) Lars Persson)  

## 0.1.2 (2026-02-24)

### Bug Fixes

-  bump openapi version ([414ea](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/414eade95a157d3) Nils Elveros)  

## 0.1.1 (2026-02-23)

### Bug Fixes

-  byter till apache connector för att hantera PATCH-operationer ([62d87](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/62d87475d2c2081) Ulf Slunga)  

## 0.1.0 (2026-02-18)

### Features

-  Split put in two operations, patch and put ([39a0a](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/39a0a08ef1fbad1) Nils Elveros)  

## 0.0.4 (2026-02-13)

### Bug Fixes

-  beslutsutfall null i kundbehovsflöde respons ([b638a](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/b638a4f59bc91ba) Ulf Slunga)  

## 0.0.3 (2026-02-12)

### Bug Fixes

-  remove dependency to framework-regel ([0f09a](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/0f09a76fe1d7f9b) Nils Elveros)  

## 0.0.2 (2026-02-11)

### Bug Fixes

-  mer mapper-metoder från framewok-regel-manuell ([dc9dd](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/dc9dd9dd64e9770) Ulf Slunga)  

## 0.0.1 (2026-02-11)

### Bug Fixes

-  redigerat package name ([c3cbb](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/c3cbb347ab6de7e) Ulf Slunga)  
-  redigerat package name ([af1ac](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/af1ac1f81f5f9f7) Ulf Slunga)  
-  lägger till kundbehovsflöde-adapter ([9f62e](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/9f62eb1ddb8796b) Ulf Slunga)  

### Other changes

**Add files via upload**


[899e5](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/899e57e638ab395) Ulf Slunga *2026-02-11 09:04:52*

**Create bundle-maven-lib-ci.yaml**


[d7ec1](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/d7ec1b41aca2990) Ulf Slunga *2026-02-11 09:04:38*

**Create CODEOWNERS**


[8ed93](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/8ed93d9416c90d3) Ulf Slunga *2026-02-11 09:01:50*

**Initial commit**


[4f484](https://github.com/Forsakringskassan/rimfrost-framework-handlaggning-adapter/commit/4f48426e76d547e) Ulf Slunga *2026-02-11 08:55:33*


