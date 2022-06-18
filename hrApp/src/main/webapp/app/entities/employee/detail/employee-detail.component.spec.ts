import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EmployeeDetailComponent } from './employee-detail.component';

describe('Employee Management Detail Component', () => {
  let comp: EmployeeDetailComponent;
  let fixture: ComponentFixture<EmployeeDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmployeeDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ employee: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(EmployeeDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EmployeeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load employee on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.employee).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
