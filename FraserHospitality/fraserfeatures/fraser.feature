Feature: Test the member signup page of fraser world
Scenario: successful Login with valid credentials
Given user launch browser
When User opens the URL"https://www.frasershospitality.com/en/fraser-world/sign-up/"
Then page title should be as"Fraser world|Mmbership sign up"

Scenario: Test the write data to excel
When create excel sheet provide valid webelement details 
Then data succesfully write to excel

Scenario: Test the screenshot comparison
When compare two images
Then succesfully compare two images

Scenario: Test the read data to from
When data fetch from excel
Then succesfully read data from excel


