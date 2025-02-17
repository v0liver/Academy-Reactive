import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DialogEditUserComponent } from '../../features/dialog-edit-user/dialog-edit-user.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public router: Router, public dialog: MatDialog) { }
  
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
