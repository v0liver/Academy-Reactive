import { Component, Inject, OnInit } from '@angular/core';
import { User } from '../../shared/model/user';
import { UserService } from '../../shared/services/user.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-create-user',
  templateUrl: './dialog-create-user.component.html',
  styleUrls: ['./dialog-create-user.component.css']
})
export class DialogCreateUserComponent implements OnInit {
user: User = {};
  job: string = '';
  name: string = '';

  constructor(private userService: UserService) {
    console.log(this.user.last_name)
   }

  ngOnInit() {
  }

  create() { 
    this.user.name=this.name;
    this.user.job=this.job;
    this.userService.addUser(this.user).subscribe({
      next:resp=>{
        alert("Utente creato con successo");
      },
      error:resp=>{
        alert("Problema con la creazione dell'utente")
      }
    })

  }

}
