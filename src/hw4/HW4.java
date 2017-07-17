/*
    Daniel Ferolino
    IS 303
    Sec 002
    HW4

    Program Description: This program will simulate a season of basketball. Home Team 
                            info will be stored in class attributes and methods can be
                            called to get and set the attributes. Every game, the user
                            will input the opponent's name. Every other game, the home
                            team will either play at home or away and will have a 5 point
                            advantage if playing at home. At the end of every season,
                            a season summary will be displayed.

 */
package hw4;

import java.util.*;
import java.text.DecimalFormat;

class BaskTeam {
    private String teamName;
    private int wins = 0;
    private int losses = 0;
    private int totalPointsFor = 0;
    private int totalPointsAgainst = 0;
    private double winLossPct = 0;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTotalPointsFor() {
        return totalPointsFor;
    }

    public void setTotalPointsFor(int totalPointsFor) {
        this.totalPointsFor = totalPointsFor;
    }

    public int getTotalPointsAgainst() {
        return totalPointsAgainst;
    }

    public void setTotalPointsAgainst(int totalPointsAgainst) {
        this.totalPointsAgainst = totalPointsAgainst;
    }

    public double getWinLossPct() {
        return winLossPct;
    }

    public void updateWinLossPct() {
        this.winLossPct = (double) wins / (double) (wins + losses);
    }
}
    

public class HW4 {
    
    public static void main(String[] args) {
        String marker;
        
        Random rndGenerator= new Random(); // RNG object
        
        int gamenumber = 1; // going to use this for the first game
        
        Scanner userinput = new Scanner(System.in);
        
        do {
            System.out.println("Would you like to simulate a basketball season? (Y/N)");
            System.out.print("--> ");
            marker = userinput.nextLine();
            if (marker.equalsIgnoreCase("y")) {

// PUT ALGORITHM HERE
                BaskTeam homeTeam = new BaskTeam(); // initialization of home team object
                
                System.out.println("\n\n");
                System.out.print("Home Team Name: ");
                String name = userinput.nextLine();
                
                homeTeam.setTeamName(name); // set the home team's name to the userinput
                
                String anothergame;
                String awayTeam; // name of the away team
                
                int homeScore = 0; // initialize home team score
                int awayScore = 0; // initialize away team score
                
                int OT = 1; // flag if overtime is needed
                
                int ot_count = 0; // how many overtimes there has been
                int anothergameerror = 1;
                
                do {
                    
                    System.out.println("\nGame " + gamenumber);
                    System.out.print("Away Team Name: ");
                    awayTeam = userinput.nextLine();
                    
                    if ((gamenumber & 1) == 0) { // even game number = hometeam is now away;
                        System.out.println("\n" + homeTeam.getTeamName() + " is AWAY");
                        homeScore = rndGenerator.nextInt(100); // home team score but theyre away
                        awayScore = rndGenerator.nextInt(100) + 5; // away team score but there home
                    }
                    else { // home team is home because game number is odd
                        System.out.println("\n" + homeTeam.getTeamName() + " is HOME");
                        homeScore = rndGenerator.nextInt(100) + 5; // random number plus 5 for home team bonus
                        awayScore = rndGenerator.nextInt(100); // away team score
                    }
                    
                    
                    if (homeScore > awayScore) {
                        System.out.println("\t" + homeTeam.getTeamName() + " " + homeScore);
                        System.out.println("\t" + awayTeam + " " + awayScore);
                        System.out.println("\n\t" + homeTeam.getTeamName() + " Wins");
                        homeTeam.setWins(homeTeam.getWins() + 1);
                    }
                    else if (awayScore > homeScore) {
                        System.out.println("\t" + homeTeam.getTeamName() + " " + homeScore);
                        System.out.println("\t" + awayTeam + " " + awayScore);
                        System.out.println("\n\t" + awayTeam + " Wins");
                        homeTeam.setLosses(homeTeam.getLosses() + 1);
                    }
                    else if (homeScore == awayScore) { // START OT ALGORITHM
                        
                        do {
                            homeScore += rndGenerator.nextInt(16);
                            awayScore += rndGenerator.nextInt(16);
                            ot_count++;
                            
                            if (homeScore > awayScore) {
                                System.out.println("\t" + homeTeam.getTeamName() + " " + homeScore);
                                System.out.println("\t" + awayTeam + " " + awayScore);
                                System.out.println(ot_count + " OT");
                                System.out.println(homeTeam.getTeamName() + " Wins");
                                ot_count = 0;
                                OT--; // turn off OT flag
                            }
                            else if (awayScore > homeScore) {
                                System.out.println("\t" + homeTeam.getTeamName() + " " + homeScore);
                                System.out.println("\t" + awayTeam + " " + awayScore);
                                System.out.println(ot_count + " OT");
                                System.out.println(awayTeam + " Wins");
                                ot_count = 0;
                                OT--; // turn off OT flag
                            }
                            
                        } while (OT >= 1);
                    } // END OT ALGORITHM
                    
                    homeTeam.updateWinLossPct(); // update the win loss pct
                    homeTeam.setTotalPointsFor(homeTeam.getTotalPointsFor() + homeScore); // update total points for
                    homeTeam.setTotalPointsAgainst(homeTeam.getTotalPointsAgainst() + awayScore); // update total points against
                    gamenumber++; // increment the game number
                    do {
                        
                        System.out.println("\n");
                        System.out.println("Another Game? (Y/N)");
                        System.out.print("--> ");
                        anothergame = userinput.nextLine();
                        if (anothergame.equalsIgnoreCase("y"))
                            anothergameerror--;
                        else if (anothergame.equalsIgnoreCase("n"))
                            anothergameerror--;
                        else if (anothergame != "y" || anothergame != "n") {
                            System.out.println("Invalid option...try again.\n");
                            anothergameerror = 1;
                        }
                        
                    } while (anothergameerror == 1);
                    
                    
                } while (anothergame.equalsIgnoreCase("y"));
                
                
                // SEASON OVER
                // Print Season Summary
              
                System.out.println("\nSeason Summary\nTeam Name\tW\tL\tPct  \tPoints For\tPoints Against");
                System.out.println("---------\t-\t-\t-----\t----------\t--------------");
     
                // format Team Name
                int numberOfSpaces = (16 - homeTeam.getTeamName().length());
                System.out.print(homeTeam.getTeamName());
                int i = 0; // space iterator
                for (i = 0; i < numberOfSpaces; i++) {
                    System.out.print(" ");
                }
                
                // format wins and losses
                
                if (homeTeam.getWins() > 10) {
                    numberOfSpaces = 6;
                }
                else if (homeTeam.getWins() > 100) {
                    numberOfSpaces = 5;
                }
                else {
                    numberOfSpaces = 7;
                }
                
                System.out.print(homeTeam.getWins());
                for (i = 0; i < numberOfSpaces; i++) {
                    System.out.print(" ");
                }
                
                System.out.print(homeTeam.getLosses());
                if (homeTeam.getLosses() > 10) {
                    numberOfSpaces = 6;
                }
                else if (homeTeam.getWins() > 100) {
                    numberOfSpaces = 5;
                }
                else {
                    numberOfSpaces = 7;
                }
                for (i = 0; i < numberOfSpaces; i++) {
                    System.out.print(" ");
                }
                
                // format PCT
                
                DecimalFormat df = new DecimalFormat("#.###");
                System.out.print(df.format(homeTeam.getWinLossPct()) + "\t");

                // format Points For
                System.out.print(homeTeam.getTotalPointsFor());
                if (homeTeam.getTotalPointsFor() > 0) {
                    numberOfSpaces = 14;
                }
                else if (homeTeam.getTotalPointsFor() > 10) {
                    numberOfSpaces = 13;
                }
                else if (homeTeam.getTotalPointsFor() > 100) {
                    numberOfSpaces = 11;
                }
                
                for (i = 0; i < numberOfSpaces; i++) {
                    System.out.print(" ");
                }
                
                System.out.print(homeTeam.getTotalPointsAgainst() + "\n\n");      
                
                System.exit(0);
            }
            if (marker.equalsIgnoreCase("n")) {
                System.out.println("Bye!");
                System.exit(0);
            }
            else {
                System.out.println("Invalid option...try again.\n");
            }
            
        } while (!marker.equalsIgnoreCase("y") || !marker.equalsIgnoreCase("n"));
        
    }
       
}
