import { SportService } from 'shard/sport.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  matchs: any[] = [];
  constructor(private service: SportService) { }

  ngOnInit() {
    this.getMatchNotPlayed();
  }

  getMatchNotPlayed() {
    this.service.getMatchNotPlayed().subscribe( matchs => {
      if(matchs.length) {
        this.service.getFootBallTeams("FootBall").subscribe( teams => {
          if(teams.length) {
            matchs.map( m => {
              let participe = []
              teams.map(team => {
                if (team.participe.value === m.match.value)
                  participe.push(team)
              });
              m.participe = participe
              this.matchs.push(m)
            })
          }
        })
      }
    });
  }

}
