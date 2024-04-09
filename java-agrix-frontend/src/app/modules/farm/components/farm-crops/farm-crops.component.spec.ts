import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmCropsComponent } from './farm-crops.component';

describe('FarmCropsComponent', () => {
  let component: FarmCropsComponent;
  let fixture: ComponentFixture<FarmCropsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FarmCropsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FarmCropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
