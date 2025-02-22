import { Component, Inject, inject, Injectable, OnInit } from '@angular/core';
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { EnumNotificationType } from '../enum/enum-notification-type';



@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {
  message: string = '';
  typeNotification?:EnumNotificationType;
  protected readonly enumNotificationType = EnumNotificationType;

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {
    this.message = data.message;
    this.typeNotification = data.type;
  }

  ngOnInit() {
  }

}
