import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobDetailComponent } from './job-detail.component';

describe('Job Management Detail Component', () => {
  let comp: JobDetailComponent;
  let fixture: ComponentFixture<JobDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ job: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(JobDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(JobDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load job on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.job).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
