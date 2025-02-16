import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from '../../shared/model/user';
import { DialogCreateUserComponent } from '../dialog-create-user/dialog-create-user.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private router: Router, public dialog: MatDialog) { }

  ngOnInit() {
  }

  navigateUser(){
    this.router.navigate(['/lista-utenti']);
  }

  openDialog(){
      const dialogRef = this.dialog.open(DialogCreateUserComponent, {
        // autoFocus: true,// per aria-hidden??
        width: '260px',
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog was closed');
      });
  
    }

}
