import React from 'react';
import styles from './SchoolTable.module.css'
import type {School} from "../shared/types/school.ts";
import {SchoolType} from "../shared/enums/SchoolType.ts";

interface SchoolTableProps {
    schools: School[];
    onDeactivate: (id: number) => void;
}

const getSchoolDisplayType = (typeKey: string): string => {
    if (typeKey in SchoolType) {
        return SchoolType[typeKey as keyof typeof SchoolType];
    }
    return typeKey;
};

const SchoolTable: React.FC<SchoolTableProps> = ({schools, onDeactivate}) => {
    return (
        <table className={styles.schoolTable}>
            <thead>
            <tr>
                <th>ID</th>
                <th>Назва</th>
                <th>ЄДРПОУ</th>
                <th>Область</th>
                <th>Тип</th>
                <th>Активний</th>
                <th>Дії</th>
            </tr>
            </thead>
            <tbody>
            {schools.map((school) => (
                <tr key={school.id}>
                    <td>{school.id}</td>
                    <td>{school.name}</td>
                    <td>{school.edrpou}</td>
                    <td>{school.region}</td>
                    <td>{getSchoolDisplayType(school.type)}</td>
                    <td>{school.isActive ? 'Так' : 'Ні'}</td>
                    <td className={styles.actionsCell}>
                        {school.isActive && (
                            <button
                                onClick={() => onDeactivate(school.id)}
                                className={styles.deactivateButton}
                            >
                                Деактивувати
                            </button>
                        )}
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};

export default SchoolTable;