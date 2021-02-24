package sample;

public class WindowTrendsThread extends Thread{
    String leagueName;
    String homeTeam;
    String awayTeam;

    public WindowTrendsThread(String leagueName, String homeTeam, String awayTeam){
        this.leagueName = leagueName;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    @Override
    public void run(){
        Settings settings = Settings.getSettingsFromFile();

        Selector selectorHT = new Selector();
        Selector selectorAT = new Selector();
        String allOrHA = "Все матчи";

        if (settings.trendsHA){
            selectorHT.getListOfMatches(leagueName, homeTeam, "Дома", Settings.getCurrentSeasonInLeague(leagueName), "Весь сезон");
            selectorAT.getListOfMatches(leagueName, awayTeam, "На выезде", Settings.getCurrentSeasonInLeague(leagueName), "Весь сезон");
            allOrHA = "Дом - выезд";
        } else {
            selectorHT.getListOfMatches(leagueName, homeTeam, "Все матчи", Settings.getCurrentSeasonInLeague(leagueName), "Весь сезон");
            selectorAT.getListOfMatches(leagueName, awayTeam, "Все матчи", Settings.getCurrentSeasonInLeague(leagueName), "Весь сезон");
        }

        selectorHT.getPList(selectorHT.listOfMatches, homeTeam);
        selectorAT.getPList(selectorAT.listOfMatches, awayTeam);

        WindowTrendsForTwoTeams wttt = new WindowTrendsForTwoTeams(homeTeam, awayTeam, Settings.getCurrentSeasonInLeague(leagueName),
                allOrHA, "Весь сезон", selectorHT, selectorAT);
        wttt.setVisible(true);
    }
}
