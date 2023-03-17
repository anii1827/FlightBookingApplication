import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FligthbookingsearchComponent } from './fligthbookingsearch.component';

describe('FligthbookingsearchComponent', () => {
  let component: FligthbookingsearchComponent;
  let fixture: ComponentFixture<FligthbookingsearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FligthbookingsearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FligthbookingsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
