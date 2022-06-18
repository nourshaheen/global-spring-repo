import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILocation, Location } from '../location.model';

import { LocationService } from './location.service';

describe('Location Service', () => {
  let service: LocationService;
  let httpMock: HttpTestingController;
  let elemDefault: ILocation;
  let expectedResult: ILocation | ILocation[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LocationService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      streetAddress: 'AAAAAAA',
      postalCode: 'AAAAAAA',
      city: 'AAAAAAA',
      stateProvince: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Location', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Location()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Location', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          streetAddress: 'BBBBBB',
          postalCode: 'BBBBBB',
          city: 'BBBBBB',
          stateProvince: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Location', () => {
      const patchObject = Object.assign(
        {
          streetAddress: 'BBBBBB',
          postalCode: 'BBBBBB',
          city: 'BBBBBB',
        },
        new Location()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Location', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          streetAddress: 'BBBBBB',
          postalCode: 'BBBBBB',
          city: 'BBBBBB',
          stateProvince: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Location', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addLocationToCollectionIfMissing', () => {
      it('should add a Location to an empty array', () => {
        const location: ILocation = { id: 123 };
        expectedResult = service.addLocationToCollectionIfMissing([], location);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(location);
      });

      it('should not add a Location to an array that contains it', () => {
        const location: ILocation = { id: 123 };
        const locationCollection: ILocation[] = [
          {
            ...location,
          },
          { id: 456 },
        ];
        expectedResult = service.addLocationToCollectionIfMissing(locationCollection, location);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Location to an array that doesn't contain it", () => {
        const location: ILocation = { id: 123 };
        const locationCollection: ILocation[] = [{ id: 456 }];
        expectedResult = service.addLocationToCollectionIfMissing(locationCollection, location);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(location);
      });

      it('should add only unique Location to an array', () => {
        const locationArray: ILocation[] = [{ id: 123 }, { id: 456 }, { id: 53343 }];
        const locationCollection: ILocation[] = [{ id: 123 }];
        expectedResult = service.addLocationToCollectionIfMissing(locationCollection, ...locationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const location: ILocation = { id: 123 };
        const location2: ILocation = { id: 456 };
        expectedResult = service.addLocationToCollectionIfMissing([], location, location2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(location);
        expect(expectedResult).toContain(location2);
      });

      it('should accept null and undefined values', () => {
        const location: ILocation = { id: 123 };
        expectedResult = service.addLocationToCollectionIfMissing([], null, location, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(location);
      });

      it('should return initial array if no Location is added', () => {
        const locationCollection: ILocation[] = [{ id: 123 }];
        expectedResult = service.addLocationToCollectionIfMissing(locationCollection, undefined, null);
        expect(expectedResult).toEqual(locationCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
