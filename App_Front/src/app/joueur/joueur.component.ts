import { SportService } from './../../shard/sport.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-joueur',
  templateUrl: './joueur.component.html',
  styleUrls: ['./joueur.component.css']
})
export class JoueurComponent implements OnInit {
  players: any[] = [];
  firstname: string;
  lastname: string;
  club: string= '';
  constructor(private service: SportService, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
      this.club = params['name'];
  });
}

  ngOnInit() {
    this.getAllPlayers();
  }

  getAllPlayers() {
    this.service.getAllPlayers().subscribe( res => this.players = res);
  }

  searchPlayers() {
    this.service.findPlayer(this.lastname, this.firstname).subscribe( res =>  this.players = res);
  }
}
