import { SportService } from './../../shard/sport.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrls: ['./club.component.css']
})
export class ClubComponent implements OnInit {
  clubs: any[] = [];
  sport : String ="";
  teamName : any;
  constructor(private service: SportService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
        this.sport = params['name'];
    });
  }

  ngOnInit() {
    this.getFootBallTeams();

  }

  getFootBallTeams() {
    this.service.getFootBallTeams(this.sport).subscribe( res => {
      this.clubs = res;
    } );
  }

  searchTeams() {
    this.service.findTeam(this.teamName).subscribe( res => this.clubs = res );
  }

}
