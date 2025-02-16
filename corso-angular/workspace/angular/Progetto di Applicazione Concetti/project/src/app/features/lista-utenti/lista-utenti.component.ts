import { Component, OnInit } from '@angular/core';
import { UserService } from '../../shared/services/user.service';
import { User } from '../../shared/model/user';

@Component({
  selector: 'app-lista-utenti',
  templateUrl: './lista-utenti.component.html',
  styleUrls: ['./lista-utenti.component.css']
})
export class ListaUtentiComponent implements OnInit {
  users: User[] = [];
  page: number = 1;
  pages: number = 0;
  
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.search(this.page).subscribe({
      next: resp => {
        this.users = resp.data;
        this.pages = resp.total_pages;
        console.log(this.users);
      }
    })
  }

  switchPage(page:number){
    this.userService.search(page).subscribe({
      next: resp => {
        this.users = resp.data;
        this.pages = resp.total_pages;
        console.log(this.users);
      }
    })
  }

}
