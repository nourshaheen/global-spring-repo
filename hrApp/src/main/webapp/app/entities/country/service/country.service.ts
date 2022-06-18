import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICountry, getCountryIdentifier } from '../country.model';

export type EntityResponseType = HttpResponse<ICountry>;
export type EntityArrayResponseType = HttpResponse<ICountry[]>;

@Injectable({ providedIn: 'root' })
export class CountryService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/countries');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(country: ICountry): Observable<EntityResponseType> {
    return this.http.post<ICountry>(this.resourceUrl, country, { observe: 'response' });
  }

  update(country: ICountry): Observable<EntityResponseType> {
    return this.http.put<ICountry>(`${this.resourceUrl}/${getCountryIdentifier(country) as number}`, country, { observe: 'response' });
  }

  partialUpdate(country: ICountry): Observable<EntityResponseType> {
    return this.http.patch<ICountry>(`${this.resourceUrl}/${getCountryIdentifier(country) as number}`, country, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICountry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICountry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addCountryToCollectionIfMissing(countryCollection: ICountry[], ...countriesToCheck: (ICountry | null | undefined)[]): ICountry[] {
    const countries: ICountry[] = countriesToCheck.filter(isPresent);
    if (countries.length > 0) {
      const countryCollectionIdentifiers = countryCollection.map(countryItem => getCountryIdentifier(countryItem)!);
      const countriesToAdd = countries.filter(countryItem => {
        const countryIdentifier = getCountryIdentifier(countryItem);
        if (countryIdentifier == null || countryCollectionIdentifiers.includes(countryIdentifier)) {
          return false;
        }
        countryCollectionIdentifiers.push(countryIdentifier);
        return true;
      });
      return [...countriesToAdd, ...countryCollection];
    }
    return countryCollection;
  }
}
