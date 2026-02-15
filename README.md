Doodle Drawing System
A simple yet interactive freehand drawing application built in Java, allowing users to create doodles, sketches, and basic artwork with mouse or touch input.
Doodle Drawing Demo
![Screenshot of the application](DemoImage.png)

Freehand drawing with mouse drag
Color picker and customizable brush sizes
Clear canvas / Erase tool
Undo / Redo functionality (coming soon)
Save drawing as PNG or JPG
Clean and minimal user interface using Java Swing or JavaFX

Technologies Used

Java (JDK 17+ recommended)
Swing / JavaFX (for GUI and canvas drawing)
VS Code with Extension Pack for Java (for development & debugging)

Getting Started
Prerequisites

Java Development Kit (JDK) 17 or higher
Visual Studio Code with Extension Pack for Java

Installation

Clone the repository:
git clone https://github.com/Alman4ck/Doodle-Drawing-System.git
Open the project in VS Code:
cd Doodle-Drawing-System
code .
Install dependencies (if using Maven/Gradle in the future; currently none required):
Just build and run via VS Code Java tools.

Running the Application

Open the main class (likely in src/doodle/Main.java or similar)
Press F5 (Debug) or use the Run button in VS Code
Alternatively, compile and run from terminal:
javac -d bin src/doodle/*.java
java -cp bin doodle.Main

Project Structure
Doodle-Drawing-System/
├── .vscode/              # VS Code settings (launch.json, settings.json)
├── src/
│   └── doodle/           # Main source code (Java packages)
│       └── Main.java     # Entry point (example)
├── .gitignore
└── README.md
Contributing
Contributions are welcome! If you'd like to add features (e.g., shapes, text, export options), follow these steps:

Fork the repository
Create a feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request

License
This project is licensed under the MIT License - see the LICENSE file for details.
