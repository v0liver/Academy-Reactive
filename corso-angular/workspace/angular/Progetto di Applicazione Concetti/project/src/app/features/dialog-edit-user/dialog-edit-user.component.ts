import { Component, Inject, OnInit } from '@angular/core';
import { User } from '../../shared/model/user';
import { UserService } from '../../shared/services/user.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-dialog-edit-user',
  templateUrl: './dialog-edit-user.component.html',
  styleUrls: ['./dialog-edit-user.component.css']
})
export class DialogEditUserComponent implements OnInit {

  user: User = {};
  job: string = '';
  name: string = '';

  constructor(private userService: UserService, @Inject(MAT_DIALOG_DATA) public data: any) {
    this.user=data.user;
    console.log(this.user.last_name)
   }

  ngOnInit() {
  }

  edit() { 
    this.user.name=this.name;
    this.user.job=this.job;
    this.userService.edit(this.user).subscribe({
      next:resp=>{
        alert("Utente modificato con successo");
      },
      error:resp=>{
        alert("Problema con la modifica dell'utente")
      }
    })

  }

}
