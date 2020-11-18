import { Component, OnInit } from '@angular/core';
import { SportService } from 'shard/sport.service';

@Component({
  selector: 'app-recent-results',
  templateUrl: './recent-results.component.html',
  styleUrls: ['./recent-results.component.css']
})
export class RecentResultsComponent implements OnInit {
  matchs: any[] = [];
  constructor(private service: SportService) { }

  ngOnInit() {
    this.getAllEndedMatch();
  }

  getAllEndedMatch() {
    this.service.getAllEndedMatch().subscribe( matchs => {
      if(matchs.length) {
        this.service.getFootBallTeams("FootBall").subscribe( teams => {
          if(teams.length) {
            matchs.map( m => {
              let participe = []
              teams.map(team => {
                if (team.participe.value === m.match.value) {
                  participe.push(team)
                }
              });
              if (participe.length) {
                m.participe = participe
                this.matchs.push(m)
              }
            })
          }
        })
      }
    });
  }

}
