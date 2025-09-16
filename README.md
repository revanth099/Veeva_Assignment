🧪 Selenium Test Automation Framework

📌 Overview

	This is a Java-based Test Automation Framework built using Selenium WebDriver, TestNG, and Extent Reports.
	The framework is designed for scalability, parallel execution, cross-browser support, and rich reporting with logs, screenshots, and custom artifacts.

🚀 Features

	✅ Page Object Model (POM) with reusable BasePage classes
	
	✅ Parallel execution using TestNG XML
	
	✅ Configurable properties via config.properties
	
	✅ Cross-browser execution (Chrome, Firefox, Edge supported)
	
	✅ Centralized Driver Management using DriverManager
	
	✅ Utility classes for waits, screenshots, file handling, scrolling, staleness handling, etc.
	
	✅ Extent Reports Integration (with logs, screenshots, and text file attachments)
	
	✅ Logging with custom log utility
	
	✅ Modular folder structure for easy maintainability

📂 Project Structure

	src/test/java/com/company/framework/
	│
	├── base/                 # Base classes
	│   └── BaseClass.java    # Common setup & teardown
	│
	├── driver/               # Driver management
	│   └── DriverManager.java
	│
	├── pages/                # Page Object Model classes
	│   ├── HomePage.java
	│   ├── MensApparelPage.java
	│   └── ...
	│
	├── tests/                # Test classes
	│   ├── Cp1Test.java
	│   ├── Dp1Test.java
	│   └── ...
	│
	├── reporting/            # Reporting related classes
	│   └── ExtentReportManager.java
	│
	├── utils/                # Utility methods
	│   ├── ConfigManager.java
	│   ├── UtilityMethods.java
	│   ├── Log.java
	│   └── ...
	│
	└── resources/
	├── configuration/
	│   └── config.properties   # Timeout, URLs, browser setup
	└── testng.xml              # TestNG suite configuration

⚙️ Prerequisites

	Java 11 or later
	
	Maven 3.x
	
	Chrome/Firefox/Edge browsers



🔧 Setup & Installation

Clone the repository:

	git clone <repo-url>
	cd automation-framework


Install dependencies:

	mvn clean install


Configure environment in config.properties:

	baseUrl=https://example.com
	pageLoadTimeout=30
	implicitWait=10

▶️ Running Tests

	Run all tests:
	mvn clean test

Run a specific suite:

	mvn clean test -DsuiteXmlFile=testng.xml
