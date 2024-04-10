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

  constructor(
    private route: ActivatedRoute,
    private farmService: FarmService,
  ) {}

  ngOnInit() {
    this.getFarmById();
  }

  private getFarmById(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.farmService.getFarmById(id).subscribe((farm) => {
      this.farm = farm;
      console.log(farm);
    });
  }
}
