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
  buttonName: string = '';
  title: string = '';
  isCreate: boolean = true;
  user: User = {};
  job: string = '';
  name: string = '';

  constructor(private userService: UserService, @Inject(MAT_DIALOG_DATA) public data: { user: User }) {
    if (data) {
      this.user = data.user;
      console.log(this.user.last_name)
    }
  }

  ngOnInit() {
    if (this.data?.user) {
      this.buttonName='Edit';
      this.title = 'Edit User ' + this.user.first_name;
      this.isCreate = false;
    } else {
      this.buttonName='Create';
      this.title = 'Create User';
    }

  }

  funzione() {
    if (this.isCreate) {
      this.edit()
    } else {
      this.create()
    }
  }

  edit() {
    this.user.name = this.name;
    this.user.job = this.job;
    this.userService.edit(this.user).subscribe({
      next: resp => {
        alert("Utente modificato con successo");
      },
      error: resp => {
        alert("Problema con la modifica dell'utente")
      }
    })

  }

  create() {
    this.user.name = this.name;
    this.user.job = this.job;
    this.userService.addUser(this.user).subscribe({
      next: resp => {
        alert("Utente creato con successo");
      },
      error: resp => {
        alert("Problema con la creazione dell'utente")
      }
    })

  }

}
