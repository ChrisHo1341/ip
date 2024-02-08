
public class ChatBot {
    private static final String INDENT = "    ";
    private static final String NEW_LINE = INDENT + "____________________________________________________________ \n";
    private static final String GREETING = INDENT + "Good day good sir! I am Chatimous Maximous, here to help you with your every need!\n";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String UNMARK = "unmark";
    private static final String MARK = "mark";
    private static final String EXIT = INDENT + "It has been a pleasure! I do hope to see you again! \n";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String PRAISE = NEW_LINE + "A job well done Sir! \n" + NEW_LINE;
    private static final int SEVEN = 7;
    private static final String ENCOURAGEMENT = NEW_LINE + "Don't fret Sir! You'll get it soon \n" + NEW_LINE;
    private static final int FIVE = 5;
    private static final String EVENT = "event";
    private static final String ADD_TASK = " has been accounted for! \n" + NEW_LINE;
    private static final String NORMAL = "normal";

    TaskList taskList = new TaskList();
    Boolean isFinished = false;
    int idx;
    Task task;

    public ChatBot() {
        System.out.println(NEW_LINE + GREETING);
    }

    public Boolean hasFinished() {
        return isFinished;
    }

    public String interact(String input) throws UnrecognizedException, MissingInputException {
        if (input.contains(LIST)) {
            return NEW_LINE + taskList.showList() + NEW_LINE;

        } else if (input.contains(UNMARK)) {
            idx = Integer.valueOf(input.substring(SEVEN));
            taskList.unmarkTask(idx);
            return ENCOURAGEMENT;

        } else if (input.contains(MARK)) {
            idx = Integer.valueOf(input.substring(FIVE));
            taskList.markTask(idx);
            return PRAISE;
        } else if (input.contains(BYE)) {
            this.isFinished = true;
            return EXIT;
        } else {
            if (input.contains(TODO)) {
                try {
                    task = new Task(input.substring(TODO.length() + 1), TODO);
                } catch (StringIndexOutOfBoundsException e) {
                     throw new MissingInputException("Life is liddat");
                }
            } else if (input.contains(DEADLINE)) {
                try {
                    task = new Task(input.substring(DEADLINE.length() + 1), DEADLINE);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new MissingInputException("Bruh");
                }
            } else if (input.contains(EVENT)) {
                try {
                    task = new Task(input.substring(EVENT.length() + 1), EVENT);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new MissingInputException("Haiz");
                }
            } else {
                // task = new Task(input, NORMAL);
                throw new UnrecognizedException("Yoyoyo");
            }
            taskList.addTask(task);
            return NEW_LINE +  task.getName() + ADD_TASK;
        }
    }

}
