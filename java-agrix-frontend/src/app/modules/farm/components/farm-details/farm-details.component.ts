import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FarmService } from '../../services/farm.service';

@Component({
  selector: 'app-farm-details',
  templateUrl: './farm-details.component.html',
  styleUrl: './farm-details.component.css',
})
export class FarmDetailsComponent {
  id!: string | null;
  farm: any;
  crops!: any[];

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getFarmById();
    this.gerCropsByFarmId();
  }

  private getFarmById(): void {
    this.farmService.getFarmById(this.id).subscribe((farm) => {
      this.farm = farm;
      console.log(farm);
    });
  }

  private gerCropsByFarmId(): void {
    this.farmService.getCropsByFarmId(this.id).subscribe((crops) => {
      this.crops = crops;
      console.log(crops);
    });
  }
}
