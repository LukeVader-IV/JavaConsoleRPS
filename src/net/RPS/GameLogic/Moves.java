package net.RPS.GameLogic;

public enum Moves {
    ROCK(new String[]   {"    _______ ",
                         "___/  _____\\",
                         "      (____)",
                         "      (____)",
                         "     (____) ",
                         "____(___)   "}),


    PAPER(new String[] {"    _______        ",
                        "___/   ____\\_____  ",
                        "        _________) ",
                        "        __________)",
                        "       _________)  ",
                        "______________)    "}),

    SCISSOR(new String[] {"    _____          ",
                          "___/  _  \\_______  ",
                          "       |_|_______) ",
                          "        __________)",
                          "     (____)        ",
                          "____(___)          "});

    private Moves weak;
    private Moves strong;

    private String[] ascii;

    private Moves(String[] ascii) {
        this.ascii = ascii;
    }

    public static void innitMoves() { //MUST BE RUN AT START !!!
        //used to initialize the weakness and strongness of the differents moves...

        //Rock
        ROCK.weak = PAPER;
        ROCK.strong = SCISSOR;

        //Paper
        PAPER.weak = SCISSOR;
        PAPER.strong = ROCK;

        //Scissor
        SCISSOR.weak = ROCK;
        SCISSOR.strong = PAPER;
    }

    public Moves getWeak() {
        return weak;
    }

    public Moves getStrong() {
        return strong;
    }

    public String[] getASCII() {
        return ascii;
    }

    public String[] getASCIIReversed() {
        String[] s = new String[6];
        char tmp;

        for(int i=0;i<s.length;++i) {
            s[i] = "";
            for(int j=ascii[i].length()-1;j>-1;--j) {
                tmp = ascii[i].charAt(j);
                
                if (tmp==')') {tmp='(';}
                else if (tmp=='/') {tmp='\\';}
                else if (tmp=='\\') {tmp='/';}

                s[i] += tmp;
            }
        }

        return s;
    }

    public static Moves getFromString(String s) {
        switch (s.toLowerCase()) {
            case "r" : return ROCK;
            case "p" : return PAPER;
            case "s" : return SCISSOR;
            default : return null;
        }
    }

    public static int processDuel(String m1, String m2) {
        Moves move1 = getFromString(m1);
        Moves move2 = getFromString(m2);

        if (move1.strong == move2) {return 0;}
        if (move2.strong == move1) {return 1;}
        
        return -1;
    }


}

