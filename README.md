# QuizApp "QuizZone" JavaFX

### Made by Michael Ploier, Dino Kupinic, Jannick Angerer, Daniel Samhaber

---

### Features:
- Music
- Stunning art
- Highscore
- Quiz Editor
- Leaderboard

How to run:
---
Navigate to \out\artifacts\QuizApp_jar and double-click it.
You can also open it in an IDE.

### Class Diagram

---

```mermaid
classDiagram
    direction BT
    class Answer {
        + Answer(String, Boolean)
    }
    class Debug {
        <<Interface>>

    }
    class EditorController {
        + EditorController()
    }
    class JsonHandler {
        + JsonHandler()
    }
    class Launcher {
        + Launcher()
    }
    class MainApplication {
        + MainApplication()
    }
    class Music {
        + Music()
    }
    class Player {
        + Player(Integer, String, Score, Score)
    }
    class Question {
+ Question(String, ArrayList~Answer~) 
}
class Quiz {
+ Quiz(String, ArrayList~Question~)
+ Quiz(String, ArrayList~Question~, ArrayList~Player~)
}
class QuizgameController {
+ QuizgameController()
}
class Score {
+ Score(double)
}
class SelectionViewController {
+ SelectionViewController()
}
class StartUpController {
+ StartUpController()
}
class Timer {
+ Timer()
}
class WindowManager {
+ WindowManager(String, String)
+ WindowManager(double, double, String, String)
+ WindowManager(Stage)
+ WindowManager(double, double, String, Scene)
}

EditorController "1" *--> "selectAnwser 1" Answer
EditorController  ..>  Answer : «create»
EditorController  ..>  Debug
EditorController "1" *--> "jsonHandler 1" JsonHandler
EditorController  ..>  JsonHandler : «create»
EditorController  ..>  Player : «create»
EditorController "1" *--> "selectedQuestion 1" Question
EditorController  ..>  Question : «create»
EditorController "1" *--> "selectedQuiz 1" Quiz
EditorController  ..>  Quiz : «create»
EditorController  ..>  Score : «create»
JsonHandler  ..>  Answer : «create»
JsonHandler  ..>  Debug
JsonHandler  ..>  Player : «create»
JsonHandler  ..>  Question : «create»
JsonHandler  ..>  Quiz : «create»
JsonHandler  ..>  Score : «create»
MainApplication  ..>  JsonHandler : «create»
MainApplication "1" *--> "mainWindow 1" WindowManager
MainApplication  ..>  WindowManager : «create»
Player "1" *--> "currentScore 1" Score
Question "1" *--> "answerArrayList *" Answer
Quiz "1" *--> "topPlayers *" Player
Quiz "1" *--> "questionArrayList *" Question
QuizgameController  ..>  JsonHandler : «create»
QuizgameController  ..>  Player : «create»
QuizgameController "1" *--> "questions *" Question
QuizgameController "1" *--> "currentScore 1" Score
QuizgameController  ..>  Score : «create»
QuizgameController  ..>  Timer : «create»
QuizgameController "1" *--> "timer 1" Timer
QuizgameController "1" *--> "question 1" WindowManager
QuizgameController  ..>  WindowManager : «create»
SelectionViewController  ..>  JsonHandler : «create»
SelectionViewController  ..>  WindowManager : «create»
StartUpController  ..>  Debug
StartUpController "1" *--> "music 1" Music
StartUpController  ..>  Music : «create»
StartUpController  ..>  WindowManager : «create»
StartUpController "1" *--> "game 1" WindowManager
Timer  ..>  Score : «create»
Timer "1" *--> "score 1" Score
WindowManager  ..>  Debug

```

### Preview images

![image](preview_images/image1.png)
![image](preview_images/image2.png)
![image](preview_images/image3.png)
![image](preview_images/image4.png)
![image](preview_images/image5.png)
