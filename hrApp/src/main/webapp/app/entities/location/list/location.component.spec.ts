import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { LocationService } from '../service/location.service';

import { LocationComponent } from './location.component';

describe('Location Management Component', () => {
  let comp: LocationComponent;
  let fixture: ComponentFixture<LocationComponent>;
  let service: LocationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [LocationComponent],
    })
      .overrideTemplate(LocationComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LocationComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(LocationService);

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
    expect(comp.locations?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
