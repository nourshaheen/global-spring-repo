import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RegionDetailComponent } from './region-detail.component';

describe('Region Management Detail Component', () => {
  let comp: RegionDetailComponent;
  let fixture: ComponentFixture<RegionDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegionDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ region: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(RegionDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RegionDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load region on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.region).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
