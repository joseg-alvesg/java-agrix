import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FarmService } from '../../services/farm.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-crop-modal',
  templateUrl: './update-crop-modal.component.html',
  styleUrls: ['./update-crop-modal.component.css'],
})
export class UpdateCropModalComponent implements OnInit {
  id!: string | null;
  @Input() showModal = false;
  @Input() farm!: any;
  farmForm!: any;
  @Output() onClose = new EventEmitter<any>();

  closeAndEmit(): void {
    this.onClose.emit(this.farmForm.value);
  }

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
  ) {
    this.farmForm = new FormGroup({
      farmName: new FormControl('', Validators.required),
      size: new FormControl('', Validators.required),
    });
  }

  ngOnInit() {}

  updateFarm(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    const farm = {
      name: this.farmForm.value.farmName,
      size: this.farmForm.value.size,
    };
    console.log(farm);
    this.farmService.updateFarm(this.id, farm).subscribe(() => {
      this.toggleModal();
    });
  }

  toggleModal() {
    this.showModal = !this.showModal;

    if (!this.showModal) {
      console.log('Emitindo evento onClose');
      console.log(this.farmForm.value);
      this.onClose.emit(this.farmForm.value);
    }
  }
}
