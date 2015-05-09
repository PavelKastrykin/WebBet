select football_match.name, football_match.time_start, 
bet_prediction, sum, current_coef, is_won, bet_status, 
money_charge 
from bets join football_match 
on bets.football_matchid = football_match.football_matchid 
