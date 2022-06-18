import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { DepartmentService } from '../service/department.service';

import { DepartmentComponent } from './department.component';

describe('Department Management Component', () => {
  let comp: DepartmentComponent;
  let fixture: ComponentFixture<DepartmentComponent>;
  let service: DepartmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [DepartmentComponent],
    })
      .overrideTemplate(DepartmentComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DepartmentComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DepartmentService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.departments?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
