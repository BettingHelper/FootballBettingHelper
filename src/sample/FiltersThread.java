package sample;

public class FiltersThread extends Thread{
    PanelMatching panelMatching;
    PanelOneTeamStats panelOneTeamStats;
    PanelConfrontation panelConfrontation;
    PanelReferee panelReferee;
    PanelTablesByLeague panelTablesByLeague;
    PanelTrends panelTrends;
    PanelBeforeAfter panelBeforeAfter;
    PanelCalculator panelCalculator;
    String league;
    String homeTeam;
    String awayTeam;


    public FiltersThread(PanelMatching panelMatching, PanelOneTeamStats panelOneTeamStats, PanelConfrontation panelConfrontation,
            PanelReferee panelReferee, PanelTablesByLeague panelTablesByLeague, PanelTrends panelTrends, PanelBeforeAfter panelBeforeAfter,
                         PanelCalculator panelCalculator, String league, String homeTeam, String awayTeam){

        this.panelMatching = panelMatching;
        this.panelOneTeamStats = panelOneTeamStats;
        this.panelConfrontation = panelConfrontation;
        this.panelReferee = panelReferee;
        this.panelTablesByLeague = panelTablesByLeague;
        this.panelTrends = panelTrends;
        this.panelBeforeAfter = panelBeforeAfter;
        this.panelCalculator = panelCalculator;
        this.league = league;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

    }

    @Override
    public void run(){
        panelMatching.setFilters(league, homeTeam, awayTeam);
        panelOneTeamStats.setFilters(league);
        panelConfrontation.setFilters(league, homeTeam, awayTeam);
        panelReferee.setFilters(league);
        panelTablesByLeague.setFilters(league);
        panelTrends.setFilters(league);
        panelBeforeAfter.setFilters(league);
        panelCalculator.setFilters(league, homeTeam, awayTeam);
    }
}