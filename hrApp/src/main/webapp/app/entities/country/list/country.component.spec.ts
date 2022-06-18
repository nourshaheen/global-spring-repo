import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { CountryService } from '../service/country.service';

import { CountryComponent } from './country.component';

describe('Country Management Component', () => {
  let comp: CountryComponent;
  let fixture: ComponentFixture<CountryComponent>;
  let service: CountryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [CountryComponent],
    })
      .overrideTemplate(CountryComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CountryComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CountryService);

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
    expect(comp.countries?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
