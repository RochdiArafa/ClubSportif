import { Component, OnInit } from '@angular/core';
import { SportService } from 'shard/sport.service';

@Component({
  selector: 'app-recent-results',
  templateUrl: './recent-results.component.html',
  styleUrls: ['./recent-results.component.css']
})
export class RecentResultsComponent implements OnInit {
  sports: any[] = [];
  constructor(private service: SportService) { }

  ngOnInit() {
    this.getAllPlayers();
  }

  getAllPlayers() {
    this.service.getAllSports().subscribe( res => this.sports = res);
  }


}
