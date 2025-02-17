import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from '../../shared/model/user';
import { DialogEditUserComponent } from '../dialog-edit-user/dialog-edit-user.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {

  constructor(private router: Router, public dialog: MatDialog) { }

  ngOnInit() {
  }

  navigateUser(){
    this.router.navigate(['/lista-utenti']);
  }

  openDialog(){
      const dialogRef = this.dialog.open(DialogEditUserComponent, {
        // autoFocus: true,// per aria-hidden??
        width: '260px',
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog was closed');
      });
  
    }

}
