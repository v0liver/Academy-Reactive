import { Component, OnInit } from '@angular/core';
import { UserService } from '../../shared/services/user.service';
import { User } from '../../shared/model/user';
import { MatDialog } from '@angular/material/dialog';
import { DialogEditUserComponent } from '../dialog-edit-user/dialog-edit-user.component';

@Component({
  selector: 'app-lista-utenti',
  templateUrl: './lista-utenti.component.html',
  styleUrls: ['./lista-utenti.component.css']
})
export class ListaUtentiComponent implements OnInit {
  users: User[] = [];
  page: number = 1;
  pages: number = 0;
  
  constructor(private userService: UserService,public dialog: MatDialog) { }

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

  openDialog(user:User){
    const dialogRef = this.dialog.open(DialogEditUserComponent, {
      autoFocus: true,
      width: '260px',
      data: { user: user } 
    });
    console.log(user);
    

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog was closed');
    });

  }

}
