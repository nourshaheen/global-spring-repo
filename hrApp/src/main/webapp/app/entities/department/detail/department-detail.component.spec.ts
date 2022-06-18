import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DepartmentDetailComponent } from './department-detail.component';

describe('Department Management Detail Component', () => {
  let comp: DepartmentDetailComponent;
  let fixture: ComponentFixture<DepartmentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartmentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ department: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DepartmentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DepartmentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load department on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.department).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
