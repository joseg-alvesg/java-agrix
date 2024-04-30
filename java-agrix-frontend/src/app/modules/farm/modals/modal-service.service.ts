import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private modalState = new BehaviorSubject<boolean>(false);

  toggleModal() {
    this.modalState.next(!this.modalState.value);
  }

  getModalState() {
    return this.modalState.asObservable();
  }
}
