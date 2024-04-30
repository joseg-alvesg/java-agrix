import { Component, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FarmService } from '../../services/farm.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ICrops } from '../../interfaces/crops';

@Component({
  selector: 'app-farm-details',
  templateUrl: './farm-details.component.html',
  styleUrl: './farm-details.component.css',
})
export class FarmDetailsComponent {
  id!: string | null;
  farm: any;
  crops!: any[];
  addCrop = false;
  cropForm!: any;
  toogleFarmUpdate: boolean = false;
  showModal: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
  ) {
    this.cropForm = new FormGroup({
      name: new FormControl('', Validators.required),
      area: new FormControl('', Validators.required),
      plantedDate: new FormControl('', Validators.required),
      harvestDate: new FormControl('', Validators.required),
    });
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getFarmById();
    this.getCropsByFarmId();
  }

  onModalClose(): void {
    this.getFarmById();
  }

  toggleModal() {
    this.showModal = !this.showModal;
  }

  private getFarmById(): void {
    this.farmService.getFarmById(this.id).subscribe((farm) => {
      this.farm = farm;
    });
  }

  private getCropsByFarmId(): void {
    this.farmService.getCropsByFarmId(this.id).subscribe((crops) => {
      this.crops = crops;
      this.getFertilizersByCropId(crops);
    });
  }

  private getFertilizersByCropId(crops: any): void {
    crops.forEach((crop: any) => {
      crop.fertilizers = this.farmService
        .getFertilizersByCropId(crop.id)
        .subscribe((fertilizers) => (crop.fertilizers = fertilizers));
    });
  }

  newCrop(): void {
    const crop: ICrops = {
      name: this.cropForm.value.name,
      plantedArea: this.cropForm.value.area,
      plantedDate: this.cropForm.value.plantedDate,
      harvestDate: this.cropForm.value.harvestDate,
    };
    this.farmService.newCrop(this.id, crop).subscribe(() => {
      this.getCropsByFarmId();
      this.addCrop = false;
    });
  }

  addCropForm(): void {
    this.addCrop = !this.addCrop;
  }
}
