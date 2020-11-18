import { Component, OnInit } from '@angular/core';
import { SportService } from 'shard/sport.service';

@Component({
  selector: 'app-sport',
  templateUrl: './sport.component.html',
  styleUrls: ['./sport.component.css']
})
export class SportComponent implements OnInit {
  sports: any[] = [];
  constructor(private service: SportService) { }

  ngOnInit() {
    this.getAllPlayers();
  }

  getAllPlayers() {
    this.service.getAllSports().subscribe( res => this.sports = res);
  }


}
